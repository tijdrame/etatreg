import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp3InfosUpdateComponent } from 'app/entities/bp-3-infos/bp-3-infos-update.component';
import { Bp3InfosService } from 'app/entities/bp-3-infos/bp-3-infos.service';
import { Bp3Infos } from 'app/shared/model/bp-3-infos.model';

describe('Component Tests', () => {
  describe('Bp3Infos Management Update Component', () => {
    let comp: Bp3InfosUpdateComponent;
    let fixture: ComponentFixture<Bp3InfosUpdateComponent>;
    let service: Bp3InfosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp3InfosUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(Bp3InfosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Bp3InfosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Bp3InfosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp3Infos(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp3Infos();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
