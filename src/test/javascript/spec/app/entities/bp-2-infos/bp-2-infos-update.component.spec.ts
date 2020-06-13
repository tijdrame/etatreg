import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp2InfosUpdateComponent } from 'app/entities/bp-2-infos/bp-2-infos-update.component';
import { Bp2InfosService } from 'app/entities/bp-2-infos/bp-2-infos.service';
import { Bp2Infos } from 'app/shared/model/bp-2-infos.model';

describe('Component Tests', () => {
  describe('Bp2Infos Management Update Component', () => {
    let comp: Bp2InfosUpdateComponent;
    let fixture: ComponentFixture<Bp2InfosUpdateComponent>;
    let service: Bp2InfosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp2InfosUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(Bp2InfosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Bp2InfosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Bp2InfosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp2Infos(123);
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
        const entity = new Bp2Infos();
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
