import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BppaysisoUpdateComponent } from 'app/entities/bppaysiso/bppaysiso-update.component';
import { BppaysisoService } from 'app/entities/bppaysiso/bppaysiso.service';
import { Bppaysiso } from 'app/shared/model/bppaysiso.model';

describe('Component Tests', () => {
  describe('Bppaysiso Management Update Component', () => {
    let comp: BppaysisoUpdateComponent;
    let fixture: ComponentFixture<BppaysisoUpdateComponent>;
    let service: BppaysisoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BppaysisoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BppaysisoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BppaysisoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BppaysisoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bppaysiso(123);
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
        const entity = new Bppaysiso();
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
