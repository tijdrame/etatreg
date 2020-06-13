import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp4InfosUpdateComponent } from 'app/entities/bp-4-infos/bp-4-infos-update.component';
import { Bp4InfosService } from 'app/entities/bp-4-infos/bp-4-infos.service';
import { Bp4Infos } from 'app/shared/model/bp-4-infos.model';

describe('Component Tests', () => {
  describe('Bp4Infos Management Update Component', () => {
    let comp: Bp4InfosUpdateComponent;
    let fixture: ComponentFixture<Bp4InfosUpdateComponent>;
    let service: Bp4InfosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp4InfosUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(Bp4InfosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Bp4InfosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Bp4InfosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp4Infos(123);
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
        const entity = new Bp4Infos();
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
